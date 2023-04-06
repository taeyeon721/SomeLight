FROM python:3.9-slim-buster

WORKDIR /app

COPY . .
RUN pip install -r requirements.txt

COPY . .

EXPOSE 5000

CMD ["python", "main.py"]

# ENTRYPOINT ["npm", "run", "serve"]
# 굳이 사용할 필요는 없어보인다.
# 어차피 정적파일을 만들텐데 왜 실행시키지?

