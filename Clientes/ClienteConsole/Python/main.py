import requests

url = 'http://localhost:8080/Escola/webresources/generic/a'
#data = '''
#    "17188"
#'''
response = requests.get(url)#, data=data)

print("aqui")
print(response, ": ", response.content)