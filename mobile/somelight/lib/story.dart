import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;


// ********StatefulWidget vs StatelessWidget
// StatefulWidget : 위젯이 동작하는 동안 Data 변경이 필요한 경우
//                  화면을 다시 그려서 변경 사항을 반영하는 동적 위젯 -> 이벤트 또는 사용자 상호작용에 의해 동작함
//                   상태 변경은 State에서 변경이 일어남(서브클래스에서 일어나지 않음) 변경할 것은 전부 State에 선언

// StatelessWidget : 화면이 로드 될 때 한번만 그려짐(말 그대로 state가 없다)
//                   변경이 필요한 Data가 없음 -> 이벤트 또는 사용자 상호 작용에 의해 동작 X
//                   해당 위젯을 상속하면 해당 위젯의 모든 것이 immutable

// ********Dart의 Coding Standard
// Class, Enum, Typedef, Type : 단어 시작 대문자
// 소스파일, 디렉토리, 라이브러리, 패키지 : 소문자 언더바
// 기타 식별자, 상수, enum : 첫단어 소문자 나머지 대문자


class StoryCreatePage extends StatefulWidget {
  const StoryCreatePage({Key? key}) : super(key: key);

  @override
  State<StoryCreatePage> createState() => _StoryCreatePageState();
}

class _StoryCreatePageState extends State<StoryCreatePage> {

  // 입력할 내용
  String inputContent = "";

  // Uri 클래스가 따로 있음
  // String type으로 하면 에러 발생

  // final baseUri = Uri(
  //     scheme:'http',
  //     host:'127.0.0.1:8080');

  // *********함수: 리턴 값에 타입이 붙음 C++ Java 같이
  // 리턴 값타입 함수 이름 (파라미터){
  //    코드;
  //    return 리턴값;
  // }
  // void : C++, Java에서와 같음 return value가 없음 / value가 없어서 타입도 없음
  // 익명함수 : () {} 꼴 / 이름 없으니 당연히 호출 불가 / 정의된 곳에서 바로 사용(void callback)


  // **********dart는 정적타입 언어, 변수 타입 틀리면 에러
  // var : 변수형 상관없이 사용 가능/ 변수형 지정되면 변경 X
  // dynamic : 변수형 상관없이 사용가능/ 변수형 지정되도 변경 O

  // ******factory 생성자 (singleton pattern)
  // singleton pattern -> 생성자를 여러번 호출해도 초기에 만든 하나의 생성자만 사용

  // 사연 Post
  Future<dynamic> postStory() async{
    final response = await http.post(Uri.http('127.0.0.1:8080','result'),
      body:<String, dynamic>{
        'content':inputContent,
        // 'result':,
        // 'keyword':,
    }).then((val) => print('val:$val'))
    .catchError((err) => print('err:$err'));

    return response;
  }

  @override
  Widget build(BuildContext context){
    //SingleChildScrollView 사용해보기
    return Scaffold(

      //****child : Container, Center (single)
      //children : Row, Column, ListView, Stack ( list of widget)
        appBar: AppBar(
          backgroundColor: Colors.green,

        ),
        body:Center(
          // 키보드 밖 터치 시 키보드 비활성 처리
        child:GestureDetector(
          onTap: ()=> FocusScope.of(context).unfocus(),
          child: Column(

            children: [
              // 입력폼
              Container(
                // *********final vs const
                // 공통점 : 재할당 불가능
                // 차이점
                // const : 컴파일 타임에서 상수를 정의
                //         (불변 강도가 더 강함 -> lifecycle 중 절대 불변/색상 글자 크기 등에 사용)
                // final : 런타임에서 상수를 정의
                //         ( 기본적으로 고정 상수지만 상황에 따라 변할 수 있음 / 위젯 생성 시점마다 변경가능)
                margin: const EdgeInsets.all(20.0),
                width: 300,
                height: 200,
                 decoration: BoxDecoration(
                   border: Border.all(
                     color: Colors.black,
                   )
                 ),
                 child: TextField(
                   // TextField 위젯의 함수 onChanged -> 파라메터 값을 setState로 저장
                   onChanged: (text){
                     setState(() {
                       inputContent = text;
                     });
                   },
                  decoration: InputDecoration(
                    labelText: 'input',
                    hintText: '사연을 입력해주세요',
                    ),
                  ),
              ),

                //*****btn
                // button : TextButton(글자만 있는 버튼)
                // OutlinedButton (테두리 있는 버튼)
                // ElevatedButton(바탕색 + 흰글씨 + 그림자)
                // ToggleButtons (그룹 중 하나만 선택)
                // Icon Button (단일 항목 선택 해제)

                // 녹음버튼
                Container(
                  child: ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      primary: Colors.red,
                    ),
                    // 구글 아이콘을 직접 사용할 수 있음!
                    onPressed: (){

                    },
                    child: Icon(Icons.mic),
                  )),
                //send 버튼
                Container(
                  child: ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      primary: Colors.indigo,
                    ),
                    onPressed: (){
                      postStory();
                    },
                    child: Text("SEND"),
                  ),
                )
              ],
          ),
        ),
        )
    );
  }
}

