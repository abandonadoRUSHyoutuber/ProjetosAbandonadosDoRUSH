import 'package:flutter/material.dart';
import 'dart:io' show Platform;

class SobreMaisPage extends StatelessWidget {
  SobreMaisPage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),
      body: Center(
        child: Container(
          padding: EdgeInsets.fromLTRB(10, 10, 10, 10),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Text('O Aplicativo esta na versão 1.0.0\n'),
              Text('A versão foi lançadia dia 28/11/2020\n'),
              Text('Checksum 1f34303045e426efebba410575f9fadd\n'),
              Text("Número de processadores do seu dispositivo: " +
                  Platform.numberOfProcessors.toString() +
                  "\n"),
              Text("Sistema operacional: " + Platform.operatingSystem + "\n"),
              Text("Versão do SO: " + Platform.operatingSystemVersion + "\n"),
              Text("Versão Geral: " + Platform.version + "\n"),
              Text("Idioma: " + Platform.localeName + "\n"),
              Text("Executavel: " + Platform.resolvedExecutable + "\n"),
            ],
          ),
        ),
      ),
    );
  }
}
