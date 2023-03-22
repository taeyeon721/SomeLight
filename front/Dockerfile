FROM node:14
WORKDIR /app
COPY . .
RUN npm install
COPY . .
RUN npm run build
ENTRYPOINT ["npm", "run", "serve"]
# 굳이 사용할 필요는 없어보인다.
# 어차피 정적파일을 만들텐데 왜 실행시키지?
# 오픈비두때문에 사용했나...?
