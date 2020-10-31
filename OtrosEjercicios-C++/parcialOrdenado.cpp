//Vector Parcialmente Ordenado
//Jesús Cencerrado Pedrero (Yisas), Enrique Román Díaz(Wike)

#include <iostream>
#include <vector>
using namespace std;

typedef struct {
	bool parcial;
	int max;
	int min;
}tPack;

tPack ordenado(const vector<int> &v, int ini, int fin)
{
	tPack mIzq, mDer;

	if ((fin - ini) == 1) {

		if (v[fin] >= v[ini]) {
			mIzq.parcial = true;
			mIzq.max = v[fin];
			mIzq.min = v[ini];
		}
		else {
			mIzq.parcial = false;
		}
	}
	else {
		int mitad = (fin + ini) >> 1;
		mIzq = ordenado(v, ini, mitad);
		mDer = ordenado(v, mitad + 1, fin);

		if (mIzq.parcial && mDer.parcial) {
			if (mIzq.min <= mDer.min && mDer.max >= mIzq.max) {
				mIzq.parcial = true;
				mIzq.max = mDer.max;
			}
			else
			{
				mIzq.parcial = false;
			}
		}
		else mIzq.parcial = false;
	}

	return mIzq;
}

/*Siendo las diferentes variables del metodo de división las siguientes:
a = número de llamadas recursivas
b = factor por el que se reduce el problema
k = coste en preparación

La resolución del problema la haremos mediante división:
2* T(n/2) +C
Siendo la a = 2, b = 2, k = 1 => el orden es O(n^k) que en nuestro caso se queda O(n).
*/
bool resuelveCaso()
{
	int nums;
	vector<int> v;
	tPack p;
	cin >> nums;

	if (nums == 0) return false;
	else {
		while (nums != 0) {
			v.push_back(nums);
			cin >> nums;
		}

		if (v.size() == 1) {
			cout << "SI\n";
			return true;
		}
		else {
			p = ordenado(v, 0, v.size() - 1);
		}

		if (p.parcial) cout << "SI\n";
		else cout << "NO\n";


		return true;
	}
	
}

int main()
{
	while (resuelveCaso());

	return 0;
}