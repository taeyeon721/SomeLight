import 'package:flutter/material.dart';
import 'package:kakao_flutter_sdk/kakao_flutter_sdk_user.dart';

import 'package:kakao_flutter_sdk_user/kakao_flutter_sdk_user.dart';
import 'package:somelight/mypage.dart';
import 'package:somelight/sample_screen.dart';
import 'package:somelight/story.dart';

void main() {

  // 웹 환경에서 카카오 로그인을 정상적으로 완료하려면 runApp() 호출 전 아래 메서드 호출 필요
  WidgetsFlutterBinding.ensureInitialized();

  // runApp() 호출 전 Flutter SDK 초기화
  KakaoSdk.init(
      nativeAppKey: 'f7176e7c4e491caaa3b9b327a213ae41',
      javaScriptAppKey: 'aa78c7b2bc6cec332bb7b843647e900b',
  );
  runApp(const SomeLight());
}

class SomeLight extends StatelessWidget{
  const SomeLight({super.key});

  @override
  Widget build(BuildContext context){
    return MaterialApp(
      // 라우팅
      // initialRoute: '/',
      // routes: {
      //   '/':(context) => const SomeLight(),
      //   '/mypage':(context) => const MyPage(),
      // },

      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.cyan,
      ),
      home: const StoryCreatePage(),
    );
  }
}



