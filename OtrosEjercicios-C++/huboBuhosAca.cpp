#include <iostream>
#include <string>
using namespace std;

bool buhos(string f)
{
	bool b = false;
	int tam = f.length();

	if (tam <= 1) b = true;
	else {
		if (f.at(0) == f.at(tam - 1))
		{
			tam--;
			f.erase(tam, 1);
			f.erase(0, 1);
			b = buhos(f);
		}
	}
	return b;
}

int main()
{
	string frase;
	int tam;
	bool h;
	
	getline(cin, frase);

	while (frase != "XXX")
	{
		tam = frase.length();

		for (int i = 0; i < tam; i++)
		{
			if (frase.at(i) == ' ')
			{
				frase.erase(i, 1);
				i--;
				tam--;
			}
			else frase.at(i) = tolower(frase.at(i));
		}

		h = buhos(frase);
		if (h) cout << "SI" << endl;
		else cout << "NO" << endl;

		getline(cin, frase);
	}

	//system("pause");
	return 0;
}