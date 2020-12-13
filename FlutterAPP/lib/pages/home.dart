import 'package:appFlutter/pages/menu_direita.dart';
import 'package:appFlutter/pages/menu_esquerda.dart';
import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  HomePage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: MenuEsquerda(),
      appBar: AppBar(
        title: Text(title),
        actions: [
          MenuDireita(),
        ],
      ),
      body: Center(
        child: Container(
          padding: EdgeInsets.fromLTRB(10, 10, 10, 10),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              ButtonTheme(
                minWidth: 170.0,
                child: RaisedButton(
                  color: Colors.white70,
                  child: Text('CONTADOR'),
                  onPressed: () {
                    Navigator.of(context).pushNamed('/contador');
                  },
                ),
              ),
              ButtonTheme(
                minWidth: 170.0,
                child: RaisedButton(
                  color: Colors.white70,
                  child: Text('SOBRE'),
                  onPressed: () {
                    Navigator.of(context).pushNamed('/sobre');
                  },
                ),
              ),
              ButtonTheme(
                minWidth: 170.0,
                child: RaisedButton(
                  color: Colors.white70,
                  child: Text('PESSOAS'),
                  onPressed: () {
                    Navigator.of(context).pushNamed('/pessoas');
                  },
                ),
              ),
              ButtonTheme(
                minWidth: 170.0,
                child: RaisedButton(
                  color: Colors.white70,
                  child: Text('CONSULTAR CEP'),
                  onPressed: () {
                    Navigator.of(context).pushNamed('/consultaCep');
                  },
                ),
              ),
              ButtonTheme(
                minWidth: 170.0,
                child: RaisedButton(
                  color: Colors.white70,
                  child: Text('CONSULTAR CNPJ'),
                  onPressed: () {
                    Navigator.of(context).pushNamed('/consultaCnpj');
                  },
                ),
              ),
              ButtonTheme(
                minWidth: 170.0,
                child: RaisedButton(
                  color: Colors.white70,
                  child: Text('CONSULTAR PLACA'),
                  onPressed: () {
                    Navigator.of(context).pushNamed('/consultaPlaca');
                  },
                ),
              ),
              ButtonTheme(
                minWidth: 170.0,
                child: RaisedButton(
                  color: Colors.white70,
                  child: Text('CONSULTAR MOEDA'),
                  onPressed: () {
                    Navigator.of(context).pushNamed('/consultaMoeda');
                  },
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
