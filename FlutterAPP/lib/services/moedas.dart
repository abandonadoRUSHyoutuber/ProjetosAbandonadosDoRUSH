import 'package:http/http.dart' as http;

class AwesomeapiServ {
  static Future getMoedaInfo(String moeda) async {
    moeda = moeda.split(" ")[0];
    var response = await http.get("http://economia.awesomeapi.com.br/$moeda/1",
        headers: {"Accept": "application/json"});
    print(response);
    if (response.statusCode == 200) {
      return response.body;
    } else {
      return "Ocorreu um erro ${response.body}";
    }
  }
}
