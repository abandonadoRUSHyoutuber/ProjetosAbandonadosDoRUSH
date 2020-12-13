import 'package:appFlutter/pages/sobre_mais.dart';
import 'package:flutter/material.dart';

class SobrePage extends StatelessWidget {
  SobrePage({Key key, this.title}) : super(key: key);
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
              Text('Informações sobre o APP'),
              RaisedButton(
                child: Text('Ver Mais Informações'),
                onPressed: () {
                  Navigator.of(context).push(MaterialPageRoute(
                      builder: (context) => SobreMaisPage(
                            title: 'Mais informações',
                          )));
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
