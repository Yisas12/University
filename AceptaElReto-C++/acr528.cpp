#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int resolver(const vector<float> &v, const int tam) {
	
	int ret = 0;
	float aux = -1;
	int i = 0;
	//bool entremedias = false;
	int cont = 0;

	while(i < tam) {
		if (v[i] == 0 || v[i] == 1 || v[i] > 1) {
			ret += i;
			if (v[i] > 1) cont++;
		}
		else if (v[i] > 0 && v[i] < 1) {
			if (aux >= v[i]) {
				cont++;
			}

			ret += i - cont;	
			aux = v[i];
			/*if (!entremedias) {
				ret += i;
				entremedias = true;
				aux = v[i];
			}
			else {
				if (aux >= v[i]) {
					ret -= 1;
				}
				
				entremedias = false;
			}*/
		}

		i++;
	}




	/*for (int i = 0; i < tam - 1; i++) {
		if ((v[i] * v[i + 1] >= v[i] && v[i] * v[i + 1] >= v[i + 1]) || (v[i] * v[i + 1] <= v[i] && v[i] * v[i + 1] <= v[i + 1])) res++;
	}

	if (ret == tam - 1) ret *= 2;
	*/

	/*for (int i = 0; i < tam; i++) {
		for (int j = i + 1; j < tam; j++) {
			if ((v[i] * v[j] >= v[i] && v[i] * v[j] >= v[j]) || (v[i] * v[j] <= v[i] && v[i] * v[j] <= v[j])) res++;
		}
	}*/

	return ret;
}

bool resuelveCaso() {
	int tam;
	float nums;
	vector<float> v;
	
	cin >> tam;

	if (tam == 0) return false;

	for (int i = 0; i < tam; i++) {
		cin >> nums;
		v.push_back(nums);
	}
	
	//sort(v.begin(), v.end());

	int p = resolver(v, tam);

	cout << p << "\n";
	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}