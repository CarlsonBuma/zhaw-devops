# docker build -t mosazhaw/node-web-app .
# docker run -p 3001:3000 --name expressapp -d mosazhaw/node-web-app

FROM node:24.11.1

# Create app directory
WORKDIR /usr/src/app

# Copy app
COPY . .

# Install
RUN npm install

# Docker Run Command
EXPOSE 3000
CMD [ "node", "server.js" ]