import 'package:http/http.dart' as http;

class ApiCarrosServ {
  static Future getPlacaInfo(String placa) async {
    var response = await http.get(
        "https://apicarros.com/v1/consulta/$placa/json",
        headers: {"Accept": "application/json"});
    print(response);
    if (response.statusCode == 200) {
      return response.body;
    } else {
      return "Ocorreu um erro ${response.body}";
    }
  }
}
