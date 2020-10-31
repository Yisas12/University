#include <iostream>
#include <vector>
using namespace std;

bool entretenido(int &casos)
{
	int aux = 0, aux2 =0;

	aux = casos % 10;

	while (casos / 10 != 0)
	{
		casos /= 10;
		aux2 = casos % 10;

		if (aux == aux2) return false;
		aux = aux2;
	}

	return true;
}

bool resuelveCaso()
{
	int casos;
	bool p;

	cin >> casos;

	if (casos == -1) return false;

	p = entretenido(casos);

	if (p) cout << "Entretenido\n";
	else cout << "No entretenido\n";

	return true;
}

int main()
{
	while (resuelveCaso());

	system("pause");
	return 0;
}