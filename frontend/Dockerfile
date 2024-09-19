FROM node:18-alpine AS builder
USER root
RUN mkdir /app
WORKDIR /app
COPY ./package.json .
COPY . .
RUN npm install
RUN npm run build

#Bundle static assets with nginx
FROM nginx:1.21.0-alpine AS production
ENV NODE_ENV production
COPY --from=builder /app/dist/angular-ecommerce /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
CMD ["nginx", "-g", "daemon off;"]
EXPOSE 80