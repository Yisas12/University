#include <iostream>
using namespace std;

bool dalton()
{
	long long int personas, cont = 0, i = 0;
	long long int dalton, aux;

	cin >> personas;
	if (personas < 2) return false;

	cin >> aux;
	if (aux < 1) return false;

	cin >> dalton;

	if (aux < dalton && aux > 0 && dalton > 0) {
		cont++;
		aux = dalton;
		i++;

		while (i < personas - 1 && cin >> dalton && dalton > 0)
		{
			if (dalton == 0) return false;
			if (aux < dalton) cont++;
			aux = dalton;
			i++;
		}
		if (dalton == 0) return false;

		if (cont == personas - 1) {
			cout << "DALTON" << endl;
		}
		else
		{
			cout << "DESCONOCIDOS" << endl;
		}
	}
	else if (aux > dalton && aux > 0 && dalton > 0) {
		cont++;
		aux = dalton;
		i++;
		while (i < personas - 1 && cin >> dalton && dalton > 0)
		{
			if (aux > dalton) cont++;
			aux = dalton;
			i++;
		}

		if (dalton == 0) return false;
		if (cont == personas - 1) {
			cout << "DALTON" << endl;
		}
		else
		{
			cout << "DESCONOCIDOS" << endl;
		}
	}
	else{
		if (aux > 0 && dalton > 0) {
			aux = dalton;
			i++;
			while (i < personas - 1 && cin >> dalton && dalton > 0)
			{
				//if (aux > dalton) cont++;
				aux = dalton;
				i++;
			}
			if (dalton == 0) return false;
			cout << "DESCONOCIDOS" << endl;
		}
		
	}

	cont = 0;
	i = 0;
	return true;
}

int main()
{
	while (dalton());

	//system("pause");
	return 0;
}