Google GSON (Maven) - para colocar Json em uma classe especifica
 //FromJson
  Gson g = new Gson();
  Person person = g.fromJson("{\"name\": \"John\"}", Person.class);
  System.out.println(person.name); //John
 //To Json
  System.out.println(g.toJson(person)); // {"name":"John"}

Update
JsonParser - para colocar em JsonObject (mais rapido se for apenas para pegar algum atributo)
  //If you want to get a single attribute out you can do it easily with the Google library as well:
  JsonObject jsonObject = new JsonParser().parse("{\"name\": \"John\"}").getAsJsonObject();
  System.out.println(jsonObject.get("name").getAsString()); //John
