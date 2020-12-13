import 'dart:io';

import 'package:appFlutter/pages/cep/index.dart';
import 'package:appFlutter/pages/cnpj/index.dart';
import 'package:appFlutter/pages/placa/index.dart';
import 'package:appFlutter/pages/moeda/index.dart';
import 'package:appFlutter/pages/contador.dart';
import 'package:appFlutter/pages/home.dart';
import 'package:appFlutter/pages/pessoa/pessoas_list.dart';
import 'package:appFlutter/pages/sobre.dart';
import 'package:flutter/material.dart';

// Gambiarra!!
// O serviço de consulta de placas (apicarros.com) usa um Certificado com problemas, então o dart rejeita a requisição.
// Para contornar esse problema então sobreescrevemos o callback padrão de tratamentos de erro do HTTP do dart.
class HttpIgnoreCertificatesErrorsOverrides extends HttpOverrides {
  @override
  HttpClient createHttpClient(SecurityContext context) {
    return super.createHttpClient(context)
      ..badCertificateCallback =
          (X509Certificate cert, String host, int port) => true;
  }
}

void main() {
  HttpOverrides.global = new HttpIgnoreCertificatesErrorsOverrides();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Aula de Flutter',
      theme: ThemeData(
        primarySwatch: Colors.teal,
      ),
      home: HomePage(title: 'Aula de Flutter'),
      routes: {
        '/home': (context) => HomePage(
              title: 'Tela Inicial',
            ),
        '/contador': (context) => ContadorPage(
              title: "Exemplo de Contador",
            ),
        '/sobre': (context) => SobrePage(
              title: "Sobre o APP",
            ),
        '/pessoas': (context) => PessoaListPage(
              title: "Pessoas",
            ),
        '/consultaCep': (context) => ConsultaCepPage(),
        '/consultaCnpj': (context) => ConsultaCnpjPage(),
        '/consultaPlaca': (context) => ConsultaPlacaPage(),
        '/consultaMoeda': (context) => ConsultaMoedaPage(),
      },
    );
  }
}
