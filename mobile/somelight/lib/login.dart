// import 'package:flutter/material.dart';


// class Login extends StatelessWidget {
//   const Login({super.key});
//
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       // appBar: AppBar(
//       //   title: const Text('Login'),
//       // ),
//       body: Center(
//         child:Column(
//             mainAxisAlignment: MainAxisAlignment.center,
//             children: <Widget>[
//               Container(
//                   margin: const EdgeInsets.all(30.0),
//                   padding: const EdgeInsets.only(bottom: 30),
//                   color: Colors.white,
//                   width: 300,
//                   height: 200,
//                   child: Image.asset('images/Group_103.png')
//               ),
//               Container(
//                 margin: const EdgeInsets.all(20.0),
//                 width: 250,
//                 height: 60,
//                 child:ButtonTheme(
//                   child:ElevatedButton(
//                       style: ElevatedButton.styleFrom(
//                         primary: Colors.grey,
//                       ),
//                       child: const Text('비회원이용', style: TextStyle(fontSize: 20, fontWeight: FontWeight.normal),),
//                       onPressed: () {
//                         Navigator.push(
//                           context,
//                           MaterialPageRoute(builder: (context) => const Story()),
//                         );
//                       }
//                   ),
//                 ),
//               ),
//
//             ]
//         ),
//       ),
//     );
//   }
// }