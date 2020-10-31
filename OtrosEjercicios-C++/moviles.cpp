#include <iostream>
using namespace std;

bool equilibrio(int pi, int di, int pd, int dd, int &cont)
{
	int pizquierda, dizquierda, dder, pder;
	bool izq = true, der = true;

	if (pi == 0)
	{
		cin >> pizquierda >> dizquierda >> pder >> dder;
		izq = equilibrio(pizquierda, dizquierda, pder, dder, pi);
	}
	if (pd == 0)
	{
		cin >> pizquierda >> dizquierda >> pder >> dder;
		der = equilibrio(pizquierda, dizquierda, pder, dder, pd);
	}

	cont = pi + pd;
	if (pi * di == pd * dd && der && izq) return true;

	return false;
}

bool resuelveCaso()
{
	int pi, di, pd, dd;
	int cont = 0;

	cin >> pi >> di >> pd >> dd;

	if (pi == 0 && di == 0 && pd == 0 && dd == 0)
		return false;
	cont = pi + pd;

	if (equilibrio(pi, di, pd, dd, cont))
		cout << "SI" << endl;
	else
		cout << "NO" << endl;
	return true;
}

int main()
{
	while (resuelveCaso());

	//system("pause");
	return 0;

}