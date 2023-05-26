import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:somelight/result.dart';
import 'package:transition/transition.dart';



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

// request
class StoryReq{
  // final String content;
  // final int result;
  // final List<Map<String,dynamic>> keyword;
  final String content;

  const StoryReq({
    required this.content,
  });

  Map<String, dynamic> toJson() {
    return{
      'content':content,
    };
  }
}

// response
class Post{
  final dynamic article;
  final dynamic keyword;
  final dynamic movie;
  final dynamic movieImage;
  final dynamic book;
  final dynamic bookImage;

  Post({this.article, this.keyword, this.movie, this.movieImage, this.book, this.bookImage});

  factory Post.fromJson(Map<String, dynamic> json) {
    return Post(
      article: Map<String,dynamic>.from(json['article']),
      keyword: json['keyword'],
      movie: json['movie'],
      movieImage: json['movieImage'],
      book: json['book'],
      bookImage: json['bookImage'],
    );
  }
}

// this vs super
// 상속을 받았다는 기준 하에 super는 부모클래스
// this는 자식 클래스 (자기자신)을 의미한다.



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
  // singleton pattern -> 생성자를 여러번 호출해도 실제로 생성되는 객체는 하나
  //                      초기에 만든 하나의 생성자만 사용


  // factory 특징 (인스턴스 창고라고 생각하면 된다)

  // 매번 인스턴스를 새로 생성하여 비용이 많이 드는 constructor와 달리 다음과 같은 특징이 있다
  // - 기존에 이미 생성된 인스턴스가 있다면 return하여 재사용
  // - 하나의 클래스에서 하나의 인스턴스만 생성 (singleton)
  // - 서브클래스 인스턴스를 리턴할 때 사용할 수 있다.
  // - Factory constructor 에서는 this에 접근 불가
  //
  // Map<String, String> headers = {
  //   "Content-Type":"application/json",
  // };

  // 사연 Post

  // *********Uri methods
  // parse(String uri, [int start = 0, int? end]) → Uri
  // uri 문자열을 파싱해서 새로운 uri 클래스를 만듦

  Future<Post> postStory(StoryReq story) async {
    try {
      final http.Response response = await http.post(
        // 안드로이드는 localhost가 10.0.2.2 임을 유의
        // 127.0.0.1, localhost 둘다 안됨 !!!!!!
        // 배포 서버 uri: https://j8a109.p.ssafy.io/result
        // 현재 배포서버에 연결
          Uri.parse('https://j8a109.p.ssafy.io/api/result'),
          body: jsonEncode(story.toJson()),
          headers: {
            "Content-Type":"application/json;charset=UTF-8",
          }
      );

      if (response.statusCode == 200) {
        print("200성공");
        return Post.fromJson(jsonDecode(utf8.decode(response.bodyBytes)));
      } else {
        print("응아니야");
        throw Exception('Failed to create post:${response.body}');
      }
    } catch (e) {
      throw Exception('Failed to create post:$e');
    }
  }

  Future<void> goResult(BuildContext context) async {
    try {
      // 현재 request를 하드코딩 해둔 상태
      StoryReq storyReq = StoryReq(
        content:this.inputContent,
      );
      // 새로운 postStory 함수를 호출해서 새로운 post를 만듦
      final Post post = await postStory(storyReq);

      // 결과 페이지로 response 데이터를 보냄
      Navigator.push(
        context,
        Transition(
          child:StoryResultPage(post: post),
          transitionEffect:TransitionEffect.LEFT_TO_RIGHT)
        );
    } catch (e) {
      print('Error creating post:$e');
    }
  }

  @override
  Widget build(BuildContext context){
    MediaQueryData deviceData = MediaQuery.of(context);
    Size screenSize = deviceData.size;

    //SingleChildScrollView 사용해보기
    return Scaffold(

      //****child : Container, Center (single)
      //children : Row, Column, ListView, Stack ( list of widget)

      //   appBar: AppBar(
      //     toolbarHeight: 65,
      //     backgroundColor: Colors.transparent,
      //     centerTitle: true,
      //     title: const Text("Story"),
      //
      //   ),
        body:Center(
          child:SingleChildScrollView(
            scrollDirection: Axis.vertical,
          child:Column(
            mainAxisSize: MainAxisSize.min,
            mainAxisAlignment: MainAxisAlignment.center,
            children:[
              // 키보드 밖 터치 시 키보드 비활성 처리
              GestureDetector(
                onTap: ()=> FocusScope.of(context).unfocus(),
                // 입력폼
                child: Container(
                  // *********final vs const
                  // 공통점 : 재할당 불가능
                  // 차이점
                  // const : 컴파일 타임에서 상수를 정의
                  //         (불변 강도가 더 강함 -> lifecycle 중 절대 불변/색상 글자 크기 등에 사용)
                  // final : 런타임에서 상수를 정의
                  //         ( 기본적으로 고정 상수지만 상황에 따라 변할 수 있음 / 위젯 생성 시점마다 변경가능)

                  padding: const EdgeInsets.all(20.0),
                  width: (0.8)*screenSize.width,
                  height:(0.6)*screenSize.height,
                   decoration: BoxDecoration(
                       gradient: LinearGradient(
                           begin: Alignment.topRight,
                           end: Alignment.bottomLeft,
                           colors: [
                             Colors.blue.shade50,
                             Colors.purple.shade50,
                             Colors.pink.shade50
                           ]
                       ),
                     borderRadius: BorderRadius.circular(20),
                   ),

                   // TextField vs TextFormField
                   // TextField에 validator 프로퍼티가 들어간 게 TextFormField
                   // 검증할 내용 전체를 Form 위젯으로 감싸고 유니크한 키를 지정
                   // GlobalKey<FromState>

                   child: TextFormField(
                     // multiline 속성이 없으면 줄바꿈이 안됨 (쭉 일렬로 작성됨)
                     keyboardType: TextInputType.multiline,
                     maxLines: null,
                     style: TextStyle(fontSize: 30),
                     // TextField 위젯의 함수 onChanged -> 파라메터 값을 setState로 저장
                     onChanged: (text){
                       setState(() {
                         inputContent = text;
                       });
                     },

                     // InputDecoration은 말그대로 input 부분만 꾸며줌(주석 해제시 검색창모양 처럼 나옴)
                     // 전체를 꾸미고 싶으면 바깥 Container나 Box를 꾸미면 됨
                     // 입력 밑줄 없애고 싶으면 아래와 같이 border: InputBorder.none 속성 부여
                    decoration: InputDecoration(
                      border: InputBorder.none,
                      // enabledBorder: OutlineInputBorder(
                      //   borderRadius: BorderRadius.all(Radius.circular(20)),
                      //   borderSide:BorderSide(color: Color(0xffF5E9CF))
                      // ),
                      // labelText: 'input',
                      hintText: '사연을 입력해주세요',
                      ),
                    ),
                ),
              ),

              //*****btn
                  // button : TextButton(글자만 있는 버튼)
                  // OutlinedButton (테두리 있는 버튼)
                  // ElevatedButton(바탕색 + 흰글씨 + 그림자)
                  // ToggleButtons (그룹 중 하나만 선택)
                  // Icon Button (단일 항목 선택 해제)

                  // 녹음버튼 - 자판에 stt 기능 거의 탑재되어 있어서 생략
              // Container(
              //     margin: const EdgeInsets.all(15.0),
              //     child: ElevatedButton(
              //       style: ElevatedButton.styleFrom(
              //         primary: Colors.red,
              //         shape: const CircleBorder(),
              //       ),
              //         // 구글 아이콘을 직접 사용할 수 있음!
              //       onPressed: (){
              //
              //       },
              //       child: Icon(
              //         Icons.mic,
              //         color: Colors.white,
              //         size: 40,),
              //     )),
                  //send 버튼
              Container(
                  margin: const EdgeInsets.all(30.0),
                child: ElevatedButton(
                  style: ElevatedButton.styleFrom(
                      padding:const EdgeInsets.all(5),
                    primary: Colors.deepPurple.shade300
                  ),
                  onPressed: (){
                    goResult(context);
                    // postStory(new StoryReq(this.inputContent, this.result, this.keyword));
                    // Navigator.push(
                    //     context,
                    //     MaterialPageRoute(builder: (context) => const StoryResultPage(data:))
                    // );
                    },
                  child: Text(
                      "결과확인",
                      style: TextStyle(
                          color:Colors.white,
                          fontSize: 30),
                    ),
                  )
                )
              ],
            ),
          ),
        ));
  }
}

