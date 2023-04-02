윈도우 환경

- j8a109.p.ssafy.io

- cmd 관리자 권한으로 실행
- pem키가 보관된 폴더로 cd(이동)
- cd C:\EC2
- ssh -i ~

-> ubuntu환경 on

패키지 리스트 목록 업데이트

- sudo apt-get update

패키지 업그레이드

- sudo apt-get upgrade

Nginx설치

- sudo apt-get install nginx

깃 설치

- sudo apt-get install git

npm 설치

- sudo apt-get install npm

vue/cli 설치

- sudo npm install -g @vue/cli

Nginx default 수정

- sudo vim /etc/nginx/sites-available/default

```
i키를 눌러 수정상태(INSERT)로 바꾼다.

root경로를 확인한다.

esc를 누르고 :wq! 를 입력한다
```

깃 클론

- root 경로로 이동한다.

- sudo git clone -b 브랜치이름 https://lab.ssafy.com/s08-ai-speech-sub2/S08P22A109.git

pakage.json이 위치한 폴더로 이동

- sudo npm i
- sudo npm run build

에러발생시 대부분 미설설치 혹은 vue/cli와 nodejs버전이 맞지 않는 문제이다.

다시 nginx 설정

```
root var/www/html/S08P22A109/front/dist;

index index.html;

tryfiles $url $url/ /index.html;
```

엔진엑스 재실행

- sudo service nginx reload
- sudo servie nginx restart

접속한다면 vue가 정상적으로 보인다.

만약 500에러라면 root경로가 잘못되어있거나 default설정을 잘못하는 등 문제가 있다.

여기까지가 프론트 수동배포이다.

---

여기서부터는 HTTPS적용하기
사용도구: Let's Encrpyt와 certbot라이브러리

certbot: let's Encrpyt를 쉽게 쓰기 위한 라이브러리

certbot 설치하기전 certbot저장소를 추가한다.

- sudo add-apt-repository ppa:certbot/certbot

certbot 설치

- sudo apt install python3-certbot-nginx

certbot을 활용하여 ssl인증서 발급

- sudo certbot --nginx -d j8a109.p.ssafy.io
  a, y, 1:https 수동 2:https 자동리다이렉트 -> 저는 그냥 2번했습니다.(별도의 default 설정을 할 필요가 없다.)

결과화면

```
IMPORTANT NOTES:
 - Congratulations!
    공개키위치
    Your certificate and chain have been saved at:
   ~
    비밀키위치
   Your key file has been saved at:
   ~

   Your cert will expire on 2023-06-14. To obtain a new or tweaked
   version of this certificate in the future, simply run certbot again
   with the "certonly" option. To non-interactively renew *all* of
   your certificates, run "certbot renew"
 - Your account credentials have been saved in your Certbot
   configuration directory at /etc/letsencrypt. You should make a
   secure backup of this folder now. This configuration directory will
   also contain certificates and private keys obtained by Certbot so
   making regular backups of this folder is ideal.
 - If you like Certbot, please consider supporting our work by:

   Donating to ISRG / Let's Encrypt:   https://letsencrypt.org/donate
   Donating to EFF:                    https://eff.org/donate-le
```

기본 인증기간은 90일(?) 이므로

- https://asecurity.dev/entry/Ubuntu-Lets-Encrypt%EC%9C%BC%EB%A1%9C-Nginx%EC%97%90-%EB%AC%B4%EB%A3%8C-SSL-%EC%A0%81%EC%9A%A9

- 다음 사이트를 참고해서 기간을 업데이트 하는 방법을 나중에 적용해봅시다.
