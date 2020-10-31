#include <iostream>
using namespace std;

bool panBodas()
{
	int numComensales, i = 0;
	char caracter;
	bool ok = true, derecha = false, izquierda = false;

	cin >> numComensales;
	if (numComensales == 0) return false;
	else if (numComensales == 1 || numComensales == 2)
	{
		cout << "TODOS COMEN" << endl;
		cin.ignore(10, '\n');
	}
	else {
		//.D.D..ID.
		while(ok && i < numComensales)
		{
			cin >> caracter;
			if (caracter == 'D') derecha = true;
			else if (caracter == 'I') izquierda = true;

			if (derecha && izquierda)
			{
				ok = false;
				cin.ignore(1000, '\n');
			}

			i++;
		}

		if (ok) cout << "TODOS COMEN" << endl;
		else cout << "ALGUNO NO COME" << endl;

		return true;
	}
}

int main()
{
	while (panBodas());

	//system("pause");
	return 0;
}