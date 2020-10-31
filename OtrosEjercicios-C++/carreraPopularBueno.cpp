#include <iostream>
#include <vector>
#include <string>
#include <cctype>
using namespace std;

void resuelveCaso()
{
	vector<char> apellido, ap2;
	string aux;
	long long int part = 0, contHermanos = 0, h = 1;
	int pos;
	bool hermanos = false;
	ap2.clear();

	do {
		getline(cin, aux);

		if (aux != "====")
		{
			part++;
			pos = aux.find_first_of(',');

			for (int j = 0; j < pos; j++)
			{
				apellido.push_back(tolower(aux[j]));
			}

			if (apellido == ap2)
			{
				++h;
				hermanos = true;
			}
			
			else if (hermanos)
			{
				contHermanos += h;
				h = 1;
				hermanos = false;
			}

			ap2 = apellido;
			apellido.clear();
			aux.clear();
		}
	} while (aux != "====");

	if (hermanos) contHermanos += h;

	cout << part << " " << contHermanos << endl;
}	

int main()
{
	int casos;

	cin >> casos;
	cin.ignore();

	for (int i = 0; i < casos; i++)
	{
		resuelveCaso();
	}

	//system("pause");
	return 0;
}