# Stage 1: Build the react application
FROM node:12.4.0-alpine as build
WORKDIR /app

# Install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy content and build the application
COPY . .
ARG REACT_APP_API_BASE_URL
ENV REACT_APP_API_BASE_URL=${REACT_APP_API_BASE_URL}
RUN npm run build

# Stage 2: Serve the React application from Nginx 
FROM nginx:1.21.6-alpine
COPY --from=build /app/build /var/www
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
EXPOSE 443
ENTRYPOINT ["nginx","-g","daemon off;"]