#include <iostream>
using namespace std;

bool ascensor()
{
	int piso, aux;
	int suma = 0;

	cin >> aux;
	//aux = piso;

	if (aux < 0) return false;

	while (cin >> piso && piso > -1) {
		
		if (aux < piso) suma += piso - aux;
		else if (aux > piso) suma += aux - piso;
		//else suma = suma;

		aux = piso;
	}

	cout << suma << endl;

	suma = 0;
	return true;
}

int main()
{
	while (ascensor());

	//system("pause");
	return 0;
}