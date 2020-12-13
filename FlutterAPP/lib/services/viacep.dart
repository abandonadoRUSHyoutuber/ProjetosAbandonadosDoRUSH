import 'package:http/http.dart' as http;

class ViaCepServ {
  static Future getCepInfo(String cep) async {
    var response = await http.get("https://viacep.com.br/ws/$cep/json/",
        headers: {"Accept": "application/json"});
    if (response.statusCode == 200) {
      return response.body;
    } else {
      return "Ocorreu um erro ${response.body}";
    }
  }
}
