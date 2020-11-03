#include<iostream>
#include<vector>
using namespace std;

int resolver(const vector<int> &v, const int &tam, const int &k) {
	int max = 0;
	int contBajas = 0;
	int cont = 0;
	int i = 0, j = 0;

	while (i < tam) {
		if (v[i] == 1) {
			j = i;
			while (j < tam) {
				cont++;

				if (v[j] == 1) {
					contBajas = 0;

					if (max < cont) max = cont;
				}
				else contBajas++;

				if (contBajas > k) break;

				i++;
				j++;
			}
		}

		contBajas = 0;
		cont = 0;
		i++;
	}


	/*while (j < tam) {
		if (v[j] == 0) {
			j++;
			contBajas++;
		}
		else {
			cont = 1;
			if (contBajas <= k) {
				cont = j - i + 1;
			}
			else {
				i = j;
			}

			if (max < cont) max = cont;
			
			contBajas = 0;
			j++;
		}
	}*/

	return max;
}

bool resuelveCaso() {
	int tam, k, nums;
	vector<int> v;

	cin >> tam >> k;

	if (tam == 0 && k == 0) return false;

	for (int i = 0; i < tam; i++) {
		cin >> nums;
		v.push_back(nums);
	}

	cout << resolver(v, tam, k) << "\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}