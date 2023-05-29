1. Nginx란?

- https://dkswnkk.tistory.com/513

- https://www.youtube.com/watch?v=6FAwAXXj5N0

- Nginx란 트래픽이 많은 웹사이트의 서버(WAS)를 도와주는 비동기 이벤트 기반구조의 경량화 **웹 서버 프로그램**입니다. 클라이언트로부터 요청을 받았을 때 요청에 맞는 정적 파일을 응답해주는 HTTP Web Server로 활용되기도 하고, 또는 Reverse Proxy Server로 활용하여 WAS의 부하를 줄일 수 있는 로드밸런서 역할을 하기도 합니다.

2. Reverse Proxy란?

- https://narup.tistory.com/238

- 간단하게 표현하면 해당서버에 프록시를 통해서 접근 시키는 방식

장점

- 로드 밸런싱 : Nginx는 클라이언트의 요청을 프록시 서버에 분산하기 위해 로드 밸런싱을 수행하여 성능, 확장성 및 신뢰성을 향상시킬 수 있습니다.
- 캐싱 : Nginx를 역방향 프록시로 사용하면 미리 렌더링된 버전의 페이지를 캐시하여 페이지 로드 시간을 단축할 수 있습니다. 이 기능은 프록시 서버의 응답에서 수신한 콘텐츠를 캐싱하고 이 콘텐츠를 사용하여 매번 동일한 콘텐츠를 프록시 서버에 연결할 필요 없이 클라이언트에 응답하는 방식으로 작동합니다.
- SSL 터미네이션 : Nginx는 클라이언트와의 연결에 대한 SSL 끝점 역할을 할 수 있습니다. 수신 SSL 연결을 처리 및 해독하고 프록시 서버의 응답을 암호화합니다.
- 압축 : 프록시 서버가 압축된 응답을 보내지 않는 경우 클라이언트로 보내기 전에 응답을 압축하도록 Nginx를 구성할 수 있습니다.
- DDoS 공격 완화 : 수신 요청과 단일 IP 주소당 연결 수를 일반 사용자에게 일반적인 값으로 제한할 수 있습니다. 또한 Nginx를 사용하면 클라이언트 위치와 "User-에이전트" 및 "Referer"와 같은 요청 헤더 값을 기준으로 액세스를 차단하거나 제한할 수 있습니다.

현재 리버스 프록시

```
ubuntu@ip-172-26-4-143:~$ sudo vim /etc/nginx/sites-available/default
server {
        #root /home/S08P22A109/front/dist;
        #index index.html;
        root /var/lib/jenkins/workspace/somelight/front/dist;
        index index.html;
        server_name j8a109.p.ssafy.io; # managed by Certbot

        # 프론트는 서버가 없다. 왜냐? 정적파일을 보고 있어서. 서버를 안씀.
        location / {
                try_files $uri $uri/ /index.html;
        }

        # api경로가 들어오면 걍 api를 떼버리고 localhost:8080/경로 로 보내버림.
        location /api{
                rewrite ^/api(.*)$ $1 break;
                proxy_pass http://localhost:8080;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header HOST $http_host;
        }
        #Certbot이 자동으로 만들어준 코드
        listen [::]:443 ssl ipv6only=on; # managed by Certbot 443포트를 인식한다면....
        listen 443 ssl; # managed by Certbot
        ssl_certificate /etc/letsencrypt/live/j8a109.p.ssafy.io/fullchain.pem; # managed by Certbot 공개키
        ssl_certificate_key /etc/letsencrypt/live/j8a109.p.ssafy.io/privkey.pem; # managed by Certbot 개인킨
        include /etc/letsencrypt/options-ssl-nginx.conf; # managed by Certbot
        ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem; # managed by Certbot
}

# 80포트로 접근시 443 포트로 리다이렉트 시켜주는 설정
server {
    if ($host = j8a109.p.ssafy.io) {
        return 301 https://$host$request_uri;
    } # managed by Certbot


        listen 80 ;
        listen [::]:80 ;
        server_name j8a109.p.ssafy.io;
        return 404; # managed by Certbot
}
```
