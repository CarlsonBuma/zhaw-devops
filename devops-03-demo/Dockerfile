FROM eclipse-temurin:25.0.1_8-jdk-noble

# Install only what is needed for build, then clean everything
RUN apt-get update && apt-get install -y --no-install-recommends \
    curl ca-certificates \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /usr/src/app

# Copy only needed parts first (better caching)
COPY frontend ./frontend
COPY backend ./backend

# ---- FRONTEND BUILD ----
RUN apt-get update && apt-get install -y --no-install-recommends nodejs npm \
  && rm -rf /var/lib/apt/lists/*

RUN cd frontend && npm ci && npm run build

# Move frontend build into backend
RUN mkdir -p backend/src/main/resources/static \
  && cp -r frontend/* backend/src/main/resources/static

# ---- BACKEND BUILD ----
RUN cd backend \
  && sed -i 's/\r$//' gradlew \
  && chmod +x gradlew \
  && ./gradlew build

# ---- REMOVE BUILD TOOLS (IMPORTANT) ----
RUN apt-get purge -y nodejs npm curl \
  && apt-get autoremove -y \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# ---- SECURITY HARDENING ----
RUN useradd -m appuser
USER appuser

EXPOSE 8080

CMD ["java", "-jar", "/usr/src/app/backend/build/libs/demo-0.0.1-SNAPSHOT.jar"]