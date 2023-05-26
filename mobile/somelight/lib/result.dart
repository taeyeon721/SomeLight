import 'package:flutter/material.dart';
import 'story.dart';
import 'dart:ui';

class StoryResultPage extends StatelessWidget{
  final Post post;

  StoryResultPage({Key? key, required this.post}) : super(key: key);

  String _imgUrl= 'assets/images';
  String _imgUrlk = 'assets/images';

  // 전구 색깔 분기
  void bulbColor(){
    if (post.article['result'] == 2){
      _imgUrl += '/greenbulb.png';
    } else if (post.article['result'] == 1){
      _imgUrl += '/navybulb.png';
    } else if (post.article['result'] == 0){
      _imgUrl += '/redbulb.png';
    }
  }

  void keywordcolor(){
    if (post.article['result'] == 2){
      _imgUrlk += '/greenheart.png';
    } else if (post.article['result'] == 1){
      _imgUrlk += '/navyheart.png';
    } else if (post.article['result'] == 0){
      _imgUrlk += '/redheart.png';
    }
  }


  @override
  Widget build(BuildContext context){
    MediaQueryData deviceData = MediaQuery.of(context);
    Size screenSize = deviceData.size;
    bulbColor();
    keywordcolor();
    return Scaffold(
      body: Center(
        child: SingleChildScrollView(
          scrollDirection: Axis.vertical,
          child: Column(
            mainAxisSize: MainAxisSize.min,
            mainAxisAlignment: MainAxisAlignment.center,

            // children:<Widget>[] vs children:[]
            // 전자는 List에 들어가는 type을 Widget으로 명시
            // 후자는 dynamic

            children:[
              // Container(
              //
              // ),
              Container(
                padding: const EdgeInsets.all(20),
                width: (0.8)*screenSize.width,
                height: (0.7)*screenSize.height,
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
                  borderRadius: BorderRadius.all(Radius.circular(10)),
                  shape:BoxShape.rectangle,
                ),
                child: Column(
                  children: <Widget>[
                    // 전구 결과
                    Container(
                      width: (0.2)*screenSize.width,
                      height: (0.2)*screenSize.width,
                      decoration: BoxDecoration(
                          color: Colors.white,
                          shape: BoxShape.circle,
                          // border: Border.all(width: 1),
                          image: DecorationImage(
                              image:AssetImage(
                                  _imgUrl
                              )
                          )
                      ),
                    ),
                    // 사연 내용
                    Text(post.article['content'],style: TextStyle(fontSize: 30)),
                    // 키워드 목록
                    Container(
                      margin: const EdgeInsets.all(10.0),
                      padding: const EdgeInsets.all(5.0),
                      child: Column(
                        children: [
                          Text("키워드", style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),),
                          Wrap(
                            spacing: 8.0,
                            runSpacing: 4.0,
                            children: [
                              for(String ent in post.keyword)
                                Chip(
                                  label: Text(
                                      ent,
                                      style: TextStyle(
                                        fontSize: 20,
                                      ),
                                  ),
                                  backgroundColor: Colors.white70,
                                ),
                            ],
                          )
                        ],
                      )
                    ),
                  ],
                ),
              ),
              Container(
                margin: const EdgeInsets.all(30.0),
                child: ElevatedButton(style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.all(5),
                  primary: Colors.deepPurple.shade300,
                ),
                  onPressed: (){
                    Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => const StoryCreatePage())
                    );
                  },
                  child: Text(
                    "다시하기",
                    style: TextStyle(
                        color:Colors.white,
                        fontSize: 30),
                  ),),
              )
            ],
          ),
        ),
      ),
    );
  }

}




