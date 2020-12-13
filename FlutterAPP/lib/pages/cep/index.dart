import 'package:appFlutter/models/cepInfo.dart';
import 'package:appFlutter/services/viacep.dart';
import 'package:appFlutter/util/fields.dart';
import 'package:flutter/material.dart';

class ConsultaCepPage extends StatefulWidget {
  @override
  _ConsultaCepPageState createState() => _ConsultaCepPageState();
}

class _ConsultaCepPageState extends State<ConsultaCepPage> {
  CepInfo cepInfo = CepInfo();
  TextEditingController cepCTRL = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Consulta de CEP"),
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
                    "Informe o CEP:",
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
                    child: textFormField(cepCTRL, "Número do CEP", "00000000",
                        TextInputType.number, 8),
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
                      ViaCepServ.getCepInfo(cepCTRL.text).then((value) {
                        setState(() {
                          cepInfo = CepInfo.fromJson(value);
                        });
                      });
                    },
                  ),
                ],
              ),
              SizedBox(
                height: 50,
              ),
              getInfoCep(),
            ],
          ),
        ));
  }

  Widget getInfoCep() {
    return cepInfo.cep == null && cepInfo.erro == null
        ? Text("Infomre o CEP para efetuar a busca!")
        : cepInfo.erro != null && cepInfo.erro
            ? Text("CEP inexistente!")
            : SingleChildScrollView(
                child: Column(
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("CEP: ", title: true),
                      textCustom(cepInfo.cep, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Estado: ", title: true),
                      textCustom(cepInfo.uf, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Cidade: ", title: true),
                      textCustom(cepInfo.localidade, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Bairro: ", title: true),
                      textCustom(cepInfo.bairro, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Logradouro: ", title: true),
                      textCustom(cepInfo.logradouro, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Complemento: ", title: true),
                      textCustom(cepInfo.complemento, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Código IBGE: ", title: true),
                      textCustom(cepInfo.ibge, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Código SIAFI: ", title: true),
                      textCustom(cepInfo.siafi, title: false),
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
