# docker build -t ammanp4/devopsdemo .
# docker run -p 9002:8080 --name dockerize-devopsdemo -d ammanp4/devopsdemo

# docker stop dockerize-devopsdemo
# docker rm dockerize-devopsdemo


FROM eclipse-temurin:25.0.1_8-jdk-noble

# ---- INSTALL DEPENDENCIES (COMBINED + CLEAN) ----
RUN apt-get update && apt-get install -y --no-install-recommends \
    curl python3.10 ca-certificates \
  && curl -sL https://deb.nodesource.com/setup_24.x | bash - \
  && apt-get install -y --no-install-recommends nodejs \
  && curl -L https://www.npmjs.com/install.sh | npm_install="11.6.2" | sh \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /usr/src/app

# ---- COPY EVERYTHING (kept as requested) ----
COPY . .

# ---- FRONTEND ----
RUN cd frontend && npm install --no-audit --no-fund

# Prepare static directory
RUN mkdir -p backend/src/main/resources/static

# Copy frontend into backend (safer than mv for caching)
RUN cp -r frontend/* backend/src/main/resources/static

# ---- BACKEND ----
RUN cd backend \
  && sed -i 's/\r$//' gradlew \
  && chmod +x gradlew \
  && ./gradlew build -x test

# ---- CLEAN TEMP FILES ----
RUN rm -rf /root/.npm /tmp/*

# ---- SECURITY (NON-ROOT USER) ----
RUN useradd -m appuser
USER appuser

EXPOSE 8080

CMD ["java", "-jar", "/usr/src/app/backend/build/libs/demo-0.0.1-SNAPSHOT.jar"]