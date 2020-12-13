import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MenuDireita extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return PopupMenuButton(
      onSelected: (value) {
        Navigator.of(context).pushNamed(value);
      },
      itemBuilder: (context) => [
        PopupMenuItem(child: Text('Contador'), value: '/contador'),
        PopupMenuItem(child: Text('Sobre'), value: '/sobre'),
        PopupMenuItem(child: Text('Pessoas'), value: '/pessoas'),
        PopupMenuItem(child: Text('Consultar CEP'), value: '/consultaCep'),
        PopupMenuItem(child: Text('Consultar CNPJ'), value: '/consultaCnpj'),
        PopupMenuItem(child: Text('Consultar Placa'), value: '/consultaPlaca'),
        PopupMenuItem(child: Text('Consultar Moeda'), value: '/consultaMoeda'),
      ],
    );
  }
}
