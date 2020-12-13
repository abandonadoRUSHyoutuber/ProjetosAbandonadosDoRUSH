import 'package:appFlutter/models/cnpjInfo.dart';
import 'package:appFlutter/services/receita_ws.dart';
import 'package:appFlutter/util/fields.dart';
import 'package:flutter/material.dart';

class ConsultaCnpjPage extends StatefulWidget {
  @override
  _ConsultaCnpjPageState createState() => _ConsultaCnpjPageState();
}

class _ConsultaCnpjPageState extends State<ConsultaCnpjPage> {
  CnpjInfo cnpjInfo = CnpjInfo();
  TextEditingController cnpjCTRL = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Consulta de CNPJ"),
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
                    "Informe o CNPJ:",
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
                    child: textFormField(cnpjCTRL, "Número do CNPJ",
                        "00000000000000", TextInputType.number, 14),
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
                      ReceitaWsServ.getCnpjInfo(cnpjCTRL.text).then((value) {
                        setState(() {
                          cnpjInfo = CnpjInfo.fromJson(value);
                        });
                      });
                    },
                  ),
                ],
              ),
              SizedBox(
                height: 50,
              ),
              getInfoCnpj(),
            ],
          ),
        ));
  }

  Widget getInfoCnpj() {
    return cnpjInfo.cnpj == null && cnpjInfo.status == null
        ? Text("Infomre o CNPJ para efetuar a busca!")
        : cnpjInfo.status != null && cnpjInfo.status != "OK"
            ? Text("CNPJ inexistente!")
            : Column(
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("CNPJ: ", title: true),
                      textCustom(cnpjInfo.cnpj, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Nome Fantasia: ", title: true),
                      textCustom(cnpjInfo.nome, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Razão Social: ", title: true),
                      textCustom(cnpjInfo.fantasia, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Tipo: ", title: true),
                      textCustom(cnpjInfo.tipo, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Data de Abertura: ", title: true),
                      textCustom(cnpjInfo.abertura, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Natureza Jurídica: ", title: true),
                      textCustom(cnpjInfo.naturezaJuridica, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Capital Social: ", title: true),
                      textCustom(cnpjInfo.capitalSocial, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Porte: ", title: true),
                      textCustom(cnpjInfo.porte, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Atividade Primaria: ", title: true),
                      textCustom(cnpjInfo.atividadePrincipal[0].text,
                          title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("CEP: ", title: true),
                      textCustom(cnpjInfo.cep, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Estado: ", title: true),
                      textCustom(cnpjInfo.uf, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Município: ", title: true),
                      textCustom(cnpjInfo.municipio, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("CEP: ", title: true),
                      textCustom(cnpjInfo.cep, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Bairro: ", title: true),
                      textCustom(cnpjInfo.bairro, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Rua: ", title: true),
                      textCustom(cnpjInfo.logradouro, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Número: ", title: true),
                      textCustom(cnpjInfo.numero, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Complemento: ", title: true),
                      textCustom(cnpjInfo.complemento, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("Telefone: ", title: true),
                      textCustom(cnpjInfo.telefone, title: false),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      textCustom("E-Mail: ", title: true),
                      textCustom(cnpjInfo.email, title: false),
                    ],
                  ),
                ],
              );
  }

  textCustom(String texto, {bool title = false}) {
    return Expanded(
        child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(texto,
            overflow: TextOverflow.clip,
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
