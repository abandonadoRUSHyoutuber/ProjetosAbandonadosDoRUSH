import 'package:appFlutter/models/placaInfo.dart';
import 'package:appFlutter/services/apiCarros.dart';
import 'package:appFlutter/util/fields.dart';
import 'package:flutter/material.dart';

class ConsultaPlacaPage extends StatefulWidget {
  @override
  _ConsultaPlacaPageState createState() => _ConsultaPlacaPageState();
}

class _ConsultaPlacaPageState extends State<ConsultaPlacaPage> {
  PlacaInfo placaInfo = PlacaInfo();
  TextEditingController placaCTRL = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Consulta de Placa"),
        ),
        body: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              SizedBox(
                height: 30,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    "Informe a Placa:",
                    style: TextStyle(fontSize: 25.5),
                  )
                ],
              ),
              SizedBox(
                height: 20,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Container(
                    height: 75,
                    width: 250,
                    child: textFormField(
                        placaCTRL, "Placa", "AAA0000", TextInputType.text, 7),
                  ),
                ],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  RaisedButton(
                    child: Text('Consultar'),
                    onPressed: () {
                      FocusScope.of(context).requestFocus(FocusNode());
                      ApiCarrosServ.getPlacaInfo(placaCTRL.text).then((value) {
                        setState(() {
                          try {
                            placaInfo = PlacaInfo.fromJson(value);
                          } catch (e) {
                            placaInfo = new PlacaInfo(
                                message: "Erro ao tentar decodificar o JSON!");
                          }
                        });
                      });
                    },
                  ),
                ],
              ),
              SizedBox(
                height: 50,
              ),
              getInfoPlaca(),
            ],
          ),
        ));
  }

  Widget getInfoPlaca() {
    return placaInfo.placa == null && placaInfo.message == null
        ? Text("Infomre a placa para efetuar a busca!")
        : placaInfo.message != null
            ? Text("Placa inexistente!")
            : SingleChildScrollView(
                child: Column(
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Placa: ", title: true),
                      textCustom(placaInfo.placa, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Estado: ", title: true),
                      textCustom(placaInfo.uf, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Município: ", title: true),
                      textCustom(placaInfo.municipio, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Marca: ", title: true),
                      textCustom(placaInfo.marca, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Modelo: ", title: true),
                      textCustom(placaInfo.modelo, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Ano: ", title: true),
                      textCustom(placaInfo.ano, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Situação: ", title: true),
                      textCustom(placaInfo.situacao, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Data: ", title: true),
                      textCustom(placaInfo.data, title: false),
                    ],
                  ),
                ],
              ));
  }

  textCustom(String texto, {bool title = false}) {
    return Expanded(
        child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(texto,
            style: title
                ? TextStyle(
                    fontSize: 17,
                    fontWeight: FontWeight.w600,
                    color: Colors.black)
                : TextStyle(fontSize: 17, color: Colors.black))
      ],
    ));
  }
}
