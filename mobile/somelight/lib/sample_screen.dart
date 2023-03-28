import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'package:kakao_flutter_sdk/kakao_flutter_sdk.dart';
import 'package:kakao_flutter_sdk_user/kakao_flutter_sdk_user.dart';
import 'package:somelight/mypage.dart';
import 'dart:developer' as developer;


class Login extends StatefulWidget{
  // {Key? key}     => {}는 optional named parameter 선언(해당 클래스 선언시 지정 불필요)
  //                    ?는 null safety
  // : super(key:key) => : s는 initializer list(리스트 초기화)
  //                     super(key:key) 상위 클래스 기본 생성자
  //                    초기화 리스트 생략 -> 상위 클래스 기본 생성자 호출
  //                    초기화 리스트 존재 -> 주어진 클래스의 특정 생성자 호출


  const Login({Key? key}) : super(key: key);


  @override
  State<Login> createState() => _LoginState();
}

class _LoginState extends State<Login> {

  //카카오톡 실행가능 여부 확인 후 실행 가능 시 카톡로그인 아니면 카카오계정 로그인
  Future<dynamic> signInWithKakao() async {
    if (await isKakaoTalkInstalled()) {
      try {
        OAuthToken token = await UserApi.instance.loginWithKakaoTalk();

        // 사용자 동의 구하는 화면 출력(카톡)
        // await AuthCodeClient.instance.authorizeWithTalk(
        //   redirectUri: "kakaof7176e7c4e491caaa3b9b327a213ae41://oauth"
        // );
        print("카톡로그인 성공");
        try {
          await AuthCodeClient.instance.authorizeWithNewScopes(
            scopes: ["account_email", "openid"],
            // openid가 활성화 되어있는 경우는 반드시 파라미터 값에 포함 시켜야함
            redirectUri: "kakaof7176e7c4e491caaa3b9b327a213ae41://oauth",);
        } catch (error) {
          print('추가 항목 동의 받기 실패 $error');
        }
        developer.log("token:"+token.toString());
        return token;
      } catch (error) {
        print("카톡 로그인 실패 $error");

        // 사용자가 카톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한경우
        // 의도적인 로그인 취소로 보고 카카오 계정으로 로그인 시도 없이 로그인 취소 처리 (ex: 뒤로가기)
        if (error is PlatformException && error.code == "CANCELED") {
          return;
        }

        // 카카오톡에 연결된 카카오 계정이 없는 경우, 카카오 계정으로 로그인
        try {
          OAuthToken token = await UserApi.instance.loginWithKakaoAccount();

          // 사용자 동의 구하는 화면 출력(카카오계정)
          // await AuthCodeClient.instance.authorize(
          //     redirectUri: "kakaof7176e7c4e491caaa3b9b327a213ae41://oauth"
          // );

          print("카카오 계정으로 로그인 성공");

          try {
            await AuthCodeClient.instance.authorizeWithNewScopes(
              scopes: ["account_email", "openid"],
              // openid가 활성화 되어있는 경우는 반드시 파라미터 값에 포함 시켜야함
              redirectUri: "kakaof7176e7c4e491caaa3b9b327a213ae41://oauth",);
          } catch (error) {
            print('추가 항목 동의 받기 실패 $error');
          }
          developer.log("token:"+token.toString());
          return token;
        } catch (error) {
          print("카카오 계정으로 로그인 실패 $error");
        }
      }
    } else {
      // 카카오톡이 설치 안된 경우 카카오 계정으로 로그인
      try {
        OAuthToken token = await UserApi.instance.loginWithKakaoAccount();

        // 사용자 동의 구하는 화면 출력(카카오계정)
        try {
          await AuthCodeClient.instance.authorizeWithNewScopes(
            scopes: ["account_email", "openid"],
            // openid가 활성화 되어있는 경우는 반드시 파라미터 값에 포함 시켜야함
            redirectUri: "kakaof7176e7c4e491caaa3b9b327a213ae41://oauth",);
        } catch (error) {
          print('추가 항목 동의 받기 실패 $error');
        }

        print("카카오 계정으로 로그인 성공");
        developer.log("token:"+token.toString());

        // mypage로 이동
        Navigator.push(
            context,
            MaterialPageRoute(builder: (context)=> const MyPage())
        );

        return token;
      } catch (error) {
        print("카카오 계정으로 로그인 실패 $error");
      }
    }
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child:Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
                margin: const EdgeInsets.all(30.0),
                padding: const EdgeInsets.only(bottom: 30),
                color: Colors.white,
                width: 300,
                height: 200,
                child: Image.asset('images/Group_103.png')
            ),
            Container(
              margin: const EdgeInsets.all(20.0),
              width: 200,
              height: 50,
              child:ButtonTheme(
                child:ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      primary: Colors.grey,
                    ),
                    child: const Text('비회원이용', style: TextStyle(fontSize: 20, fontWeight: FontWeight.normal),),
                    onPressed: () {
                      // Navigator.push(
                      //   context,
                      //   MaterialPageRoute(builder: (context) => const Story()),
                      // );
                    }
                ),
              ),),
            _loginButton(
              'images/kakao_login_large_narrow.png', signInWithKakao,
            )
          ],
        ),
      ),
    );
  }
}

  Widget _loginButton(String path, VoidCallback onTap) {
    return Card(
      elevation: 5.0,
      shape: const RoundedRectangleBorder(),
      clipBehavior: Clip.antiAlias,
      child: Ink.image(
        image: AssetImage('$path'),
        width: 200,
        height: 50,
        child: InkWell(
          borderRadius: const BorderRadius.all(
            Radius.circular(10.0),
          ),
          onTap: onTap,
        ),
      ),
    );
  }


  // @override
  // Widget build(BuildContext context) {
  //   return Scaffold(
  //     body: Center(
  //       child:Column(
  //         mainAxisAlignment: MainAxisAlignment.center,
  //         children: [
  //             Container(
  //               margin: const EdgeInsets.all(30.0),
  //               padding: const EdgeInsets.only(bottom: 30),
  //               color: Colors.white,
  //               width: 300,
  //               height: 200,
  //               child: Image.asset('images/Group_103.png')
  //             ),
  //             Container(
  //               margin: const EdgeInsets.all(20.0),
  //               width: 200,
  //               height: 50,
  //               child:ButtonTheme(
  //                 child:ElevatedButton(
  //                   style: ElevatedButton.styleFrom(
  //                   primary: Colors.grey,
  //                 ),
  //                 child: const Text('비회원이용', style: TextStyle(fontSize: 20, fontWeight: FontWeight.normal),),
  //                 onPressed: () {
  //                 // Navigator.push(
  //                 //   context,
  //                 //   MaterialPageRoute(builder: (context) => const Story()),
  //                 // );
  //                 }
  //               ),
  //             ),),
  //             _loginButton(
  //             'images/kakao_login_large_narrow.png', signInWithKakao,
  //           )
  //         ],
  //       ),
  //     ),
  //   );
  // }
  //
  // Widget _loginButton(String path, VoidCallback onTap) {
  //   return Card(
  //     elevation: 5.0,
  //     shape: const RoundedRectangleBorder(),
  //     clipBehavior: Clip.antiAlias,
  //     child: Ink.image(
  //       image: AssetImage('$path'),
  //       width: 200,
  //       height: 50,
  //       child: InkWell(
  //         borderRadius: const BorderRadius.all(
  //           Radius.circular(10.0),
  //         ),
  //         onTap: onTap,
  //       ),
  //     ),
  //   );
  // }

//   Widget _logoutButton() {
//     return ElevatedButton(
//       onPressed: signOut,
//       style: ButtonStyle(
//         backgroundColor: MaterialStateProperty.all(
//           const Color(0xff0165E1),
//         ),
//       ),
//       child: const Text('로그아웃'),
//     );
//   }
// }