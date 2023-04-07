import 'package:flutter/material.dart';
// import 'package:flutter_localizations/flutter_localizations.dart/';
// import 'package:intl/intl.dart';
// import 'package:flutter_boilerplate/generated/l10n.dart';
import 'package:somelight/story.dart';
import 'package:flutter_animator/flutter_animator.dart';
import 'package:transition/transition.dart';

void main() {

  // 웹 환경에서 카카오 로그인을 정상적으로 완료하려면 runApp() 호출 전 아래 메서드 호출 필요
  WidgetsFlutterBinding.ensureInitialized();

  // runApp() 호출 전 Flutter SDK 초기화
  // KakaoSdk.init(
  //     nativeAppKey: 'f7176e7c4e491caaa3b9b327a213ae41',
  //     javaScriptAppKey: 'aa78c7b2bc6cec332bb7b843647e900b',
  // );
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
      // localizationsDelegates: const [
      //   // S.delegate,
      //   GlobalMaterialLocalizations.delegate,
      //   GlobalWidgetsLocalizations.delegate,
      //   GlobalCupertinoLocalizations.delegate,
      // ],
      // supportedLocales: const [
      //   Locale('en', ''), //English, no country code
      //   Locale('ko', ''),
      // ],
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        fontFamily: "Dovemayo_gothic",
      ),
      home: Homepage()
    );
  }
}

class Homepage extends StatelessWidget{

  @override
  Widget build(BuildContext context){
    MediaQueryData deviceData = MediaQuery.of(context);
    Size screenSize = deviceData.size;
    return Scaffold(
      body: Center(
        child:Container(
          width: screenSize.width,
          height: screenSize.height,
          decoration: BoxDecoration(
              gradient: LinearGradient(
              begin: Alignment.topRight,
              end: Alignment.bottomLeft,
              colors: [
                Colors.blue.shade100,
                Colors.purple.shade100,
                Colors.pink.shade100
              ]
            )

          ),
          child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
              child: Text(
                "SOMELIGHT",
                style: TextStyle(
                  color: Colors.white.withOpacity(0.8),
                  fontWeight: FontWeight.bold,
                  fontSize: 65,
                ),
              ),
            ),
            Container(
              width: screenSize.width*(0.1),
              height: screenSize.height*(0.05),
            ),
            Container(
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.deepPurple.shade300,
                ),
                onPressed: ()=>{
                  Navigator.push(
                    context,
                    Transition(
                child: StoryCreatePage(),
                transitionEffect: TransitionEffect.SCALE)
                 )},
                child: Text(
                    "시작하기",
                  style: TextStyle(
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                  fontSize: 40,
                ),),
                ),
              ),
          ],
        ),
      ),
      ));
  }
}

