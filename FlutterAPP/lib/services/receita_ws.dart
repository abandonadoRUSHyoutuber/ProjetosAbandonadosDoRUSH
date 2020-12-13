import 'package:http/http.dart' as http;

class ReceitaWsServ {
  static Future getCnpjInfo(String cnpj) async {
    var response = await http.get("https://www.receitaws.com.br/v1/cnpj/$cnpj",
        headers: {"Accept": "application/json"});
    if (response.statusCode == 200) {
      return response.body;
    } else {
      return "Ocorreu um erro ${response.body}";
    }
  }
}
