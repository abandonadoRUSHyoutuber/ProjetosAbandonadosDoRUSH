import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MenuEsquerda extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        // Important: Remove any padding from the ListView.
        padding: EdgeInsets.zero,
        children: <Widget>[
          DrawerHeader(
            child: null,
            decoration: BoxDecoration(
              //color: Colors.blue,
              image: DecorationImage(
                  fit: BoxFit.fill,
                  image: AssetImage('assets/images/menu.png')),
            ),
          ),
          ListTile(
            leading: Icon(Icons.add),
            title: Text('Contador'),
            onTap: () {
              Navigator.pop(context);
              Navigator.of(context).pushNamed('/contador');
            },
          ),
          ListTile(
            leading: Icon(Icons.info),
            title: Text('Sobre'),
            onTap: () {
              Navigator.pop(context);
              Navigator.of(context).pushNamed('/sobre');
            },
          ),
          ListTile(
            leading: Icon(Icons.people),
            title: Text('Pessoas'),
            onTap: () {
              Navigator.pop(context);
              Navigator.of(context).pushNamed('/pessoas');
            },
          ),
          ListTile(
            leading: Icon(Icons.place),
            title: Text('Consultar CEP'),
            onTap: () {
              Navigator.pop(context);
              Navigator.of(context).pushNamed('/consultaCep');
            },
          ),
          ListTile(
            leading: Icon(Icons.location_city),
            title: Text('Consultar CNPJ'),
            onTap: () {
              Navigator.pop(context);
              Navigator.of(context).pushNamed('/consultaCnpj');
            },
          ),
          ListTile(
            leading: Icon(Icons.directions_car),
            title: Text('Consultar Placa'),
            onTap: () {
              Navigator.pop(context);
              Navigator.of(context).pushNamed('/consultaPlaca');
            },
          ),
          ListTile(
            leading: Icon(Icons.attach_money),
            title: Text('Consultar Moeda'),
            onTap: () {
              Navigator.pop(context);
              Navigator.of(context).pushNamed('/consultaMoeda');
            },
          ),
        ],
      ),
    );
  }
}
