import 'package:appFlutter/models/moedaInfo.dart';
import 'package:appFlutter/services/moedas.dart';
import 'package:flutter/material.dart';

class ConsultaMoedaPage extends StatefulWidget {
  @override
  _ConsultaMoedaPageState createState() => _ConsultaMoedaPageState();
}

class _ConsultaMoedaPageState extends State<ConsultaMoedaPage> {
  String dropdownValue = "USD (Dólar Comercial)";
  MoedaInfo moedaInfo = MoedaInfo();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Consulta de Cotação de Moeda"),
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
                    "Informe a Moeda:",
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
                      height: 50,
                      width: 220,
                      child: DropdownButton<String>(
                        value: dropdownValue,
                        iconSize: 24,
                        elevation: 16,
                        style: TextStyle(color: Colors.black),
                        onChanged: (String newValue) {
                          setState(() {
                            dropdownValue = newValue;
                          });
                        },
                        items: <String>[
                          "USD (Dólar Comercial)",
                          "USDT (Dólar Turismo)",
                          "CAD (Dólar Canadense)",
                          "AUD (Dólar Australiano)",
                          "EUR (Euro)",
                          "GBP (Libra Esterlina)",
                          "ARS (Peso Argentino)",
                          "JPY (Iene Japonês)",
                          "CHF (Franco Suíço)",
                          "CNY (Yuan Chinês)",
                          "YLS (Novo Shekel Israelense)",
                          "BTC (Bitcoin)",
                          "LTC (Litecoin)",
                          "ETH (Ethereum)",
                          "XRP (Ripple)",
                        ].map<DropdownMenuItem<String>>((String value) {
                          return DropdownMenuItem<String>(
                            value: value,
                            child: Text(value),
                          );
                        }).toList(),
                      )),
                ],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  RaisedButton(
                    child: Text('Consultar'),
                    onPressed: () {
                      FocusScope.of(context).requestFocus(FocusNode());
                      AwesomeapiServ.getMoedaInfo(dropdownValue).then((value) {
                        setState(() {
                          moedaInfo = MoedaInfo.fromJson(value);
                        });
                      });
                    },
                  ),
                ],
              ),
              SizedBox(
                height: 50,
              ),
              getInfoMoeda(),
            ],
          ),
        ));
  }

  Widget getInfoMoeda() {
    return moedaInfo.name == null
        ? Text("Infomre a moeda para efetuar a busca!")
        : SingleChildScrollView(
            child: Column(
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  textCustom("Nome: ", title: true),
                  textCustom(moedaInfo.name, title: false),
                ],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  textCustom("Cotação Atual: ", title: true),
                  textCustom(moedaInfo.ask, title: false),
                ],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  textCustom("Variação de Hoje: ", title: true),
                  textCustom(moedaInfo.pctChange, title: false),
                ],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  textCustom("Máxima de Hoje: ", title: true),
                  textCustom(moedaInfo.high, title: false),
                ],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  textCustom("Mínima de Hoje: ", title: true),
                  textCustom(moedaInfo.low, title: false),
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
