//Equipo: Los FAuLos

//El coste de este algoritmo es O(log(n)) ya que haces un bucle interno y otro externo y, al hacer la recursión dividiendo entre 10, se consigue
//ese orden, siendo n el número de veces que recorres el array.

#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <cmath>
using namespace std;

const long long int UNO = 1;

long long int sinUnos(const vector<long long int>& v, int fin, int i)
{
	long long int res, poten = 1;

	if (i == 0) { // Caso Base, cuando estamos en la cifra unidad

		if (v[i] <= 1) {
			res = 1;
		}
		else {
			res = v[i];
		}
	}
	else { //Caso recursivo, cifras de decena en adelante empezando por la más pesada
		if (v[i] == 1) {

			for (int k = 0; k < i; ++k) {
				poten = poten * 9;
			}

			res = poten;
		}
		else if (v[i] == 0) {
			res = sinUnos(v, fin, i - UNO);
		}
		else {
			for (int k = 0; k < i; ++k) {
				poten = poten * 9;
			}
			res = ((v[i] - UNO) * poten) + sinUnos(v, fin, i - UNO);
		}

	}
	return res;
}

int main() {
	long long int num, p;
	vector<long long int> v;

	cin >> num;

	while (cin) {

		if (num == 0) {
			p = 1;
		}
		else {
			while (num > 0)
			{
				v.push_back(num % 10);
				num = num / 10;
			}
			p = sinUnos(v, v.size() - 1, v.size() - 1);
		}

		cout << p << "\n";

		v.clear();

		cin >> num;
	}
	return 0;
}