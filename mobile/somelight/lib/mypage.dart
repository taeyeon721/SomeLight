import 'package:flutter/material.dart';
import 'package:kakao_flutter_sdk/kakao_flutter_sdk.dart';
import 'package:kakao_flutter_sdk_user/kakao_flutter_sdk_user.dart';
import 'package:somelight/sample_screen.dart';
import 'dart:developer' as developer;

class MyPage extends StatefulWidget {
  const MyPage({Key? key}) : super(key: key);

  @override
  State<MyPage> createState() => _MyPageState();
}

class _MyPageState extends State<MyPage>{

  String nickname = "";
  String email = "";
  int pk = 0;

  void accesstokenCheck() async {
    if (await AuthApi.instance.hasToken()) {
      try {
        AccessTokenInfo tokenInfo =
        await UserApi.instance.accessTokenInfo();
        print("access 토큰 유효성 체크 성공");
      } catch (error) {
        if (error is KakaoException && error.isInvalidTokenError()) {
          print("토큰만료$error");
        } else {
          print("토큰 정보 조회 실패 $error");
        }

        // 토큰 만료로 인해 로그인
        Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => const Login())
        );
      }
    } else {
      print("발급된 토큰 없음");
      Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => const Login())
      );
    }
  }


  void UserInfo() async{

    try{
      User user = await UserApi.instance.me();
      nickname = "$user.kakaoAccount?.profile?.nickname";
      email = "$user.kakaoAccount?.email";
      print("사용자 정보 요청 성공");

    } catch(error){
      print("사용자 정보 요청 실패 $error");
    }
  }

  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(UserInfo.toString()),
        backgroundColor: Colors.green,
        elevation: 1,
        shape: ContinuousRectangleBorder(
          borderRadius: const BorderRadius.only(
            bottomLeft: Radius.circular(30.0),
          ),
        ),
      ),
      body: Center(

      ),
    );
  }
}