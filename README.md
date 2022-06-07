# Sistema para monitoramento de temperatura do concreto para blocos estruturais

É uma solução IoT baseada no ESP32 com o objetivo de medir a temperatura do concreto na concretagem de blocos estruturais para que com os valores ser feita análise da ressitência.
<h2><b>Introdução</b></h2>
O concreto é formado por cimento, água, areia, brita e aditivos, sendo a reação entre cimento e água a responsável pelo endurecimento do concreto. Essa reação é exotérmica, ou seja,
libera calor, o qual chamamos de calor de hidratação.A temperatura do ambiente influencia este calor, sendo problemático tanto o calor extremo quanto o frio extremo.
Portanto a análise da temperatura é importante para medir o tempo exato de cura do concreto, evitando assim possíveis fissuras que interferem na qualidade da estrutura.
<h2><b>Diagrama de fiação </b></h2>
<u1>
  <li> Ligação dos fio para os modelos B1, B2 e B3 </li>
  <img src="https://user-images.githubusercontent.com/76061000/115427318-c0d29980-a1d7-11eb-839f-0ce94e1b56d8.png" width="800px">
  <li> Tutorial instalação:  https://youtu.be/j6668FpMcGA </li>
  <u1>
   <h2>
     <h2><b>Bibliotecas necessárias</b></h2>

|  <b>Nome</b> |  <b>Link</b> |
|---|---|
| ArduinoJson  |<a href="https://github.com/bblanchon/ArduinoJson/">GIT</a>  |     
|  WebSockets | <a href="https://github.com/Links2004/arduinoWebSockets/">GIT</a>  |  
| Sirincpro  |  <a href="https://github.com/sinricpro/esp8266-esp32-sdk/">GIT</a> |   
| DallasTemperature  |<a href="https://github.com/milesburton/Arduino-Temperature-Control-Library">GIT</a> |   

<h2><b>Hardware</b></h2>
<u1>
  <li> Blumen Touch Placa 1, 2 e 3 teclas frente e trás</li>
  <img src="https://user-images.githubusercontent.com/76061000/172374827-852379f1-d8f9-41f2-b57c-8abaf6a2782a.jpeg" width="750px">
  <img src="https://user-images.githubusercontent.com/76061000/116570529-b784b380-a8e0-11eb-9eb2-932ed905539e.png" width="807px">
  
  <u1>
