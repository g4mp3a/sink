{
  "apikey": "ae1oNw48AS7H2SBSX2pltrUWdzEEoNnNuiaEk1Xq8G5Z",
  "iam_apikey_description": "Auto-generated for key 6586647c-8193-4031-9dbe-8e9fd13e1820",
  "iam_apikey_name": "ManagerCreds",
  "iam_role_crn": "crn:v1:bluemix:public:iam::::serviceRole:Manager",
  "iam_serviceid_crn": "crn:v1:bluemix:public:iam-identity::a/84012897fe4a4bec9a69d93b4019ffba::serviceid:ServiceId-8e98ac2c-3eef-4c88-aa15-6c7dc0883849",
  "url": "https://gateway.watsonplatform.net/language-translator/api"
}

docker build -t gautampriya/node-container https://github.com/lidderupk/nodejs-docker.git

docker run -p 8080:8080 -e "nlp_key=ae1oNw48AS7H2SBSX2pltrUWdzEEoNnNuiaEk1Xq8G5Z" -d gautampriya/node-container

curl "localhost:8080/translate?text=People+are+suffering.+People+are+dying+and+dying+ecosystems+are+collapsing.+We+are+in+the+beginning+of+a+mass+extinction%2C+and+all+you+can+talk+about+is+the+money+and+fairy+tales+of+eternal+economic+growth&lang=en-pl"
{
  "translations": [
    {
      "translation": "Ludzie cierpią. Ludzie umierają, a umierające ekosystemy się zapadają. Jesteśmy na początku masowego wyginięcia, a wszystko, o czym można rozmawiać, to pieniądze i bajki o wiecznym wzroście gospodarczym"
    }
  ],
  "word_count": 39,
  "character_count": 204
}

oc login https://c100-e.us-south.containers.cloud.ibm.com:30117 --token=JYno0NCdfMLCnqJuyQhYlg6fsuIHqdYr8T1--sen-Qk