FROM node:10

# Hack to get around openshift's scc
RUN chgrp -R 0 /run && chmod -R g=u /run

# Create directory for application
WORKDIR /data/translator-app

# Install dependencies
COPY package*.json ./

RUN npm install

COPY . .

CMD [ "node", "server.js" ]

EXPOSE 8080:3000