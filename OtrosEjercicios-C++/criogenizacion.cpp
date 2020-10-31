#include <iostream>
#include <vector>
using namespace std;

/*void criogenizacion()
{
	int i = 0;
	vector <long long int> v;
	long long int temperaturas;
	int contA = 0, contB = 0, contV = 0;
	long long int max, men;

	cin >> temperaturas;

	while (temperaturas != 0)
	{
		v.push_back(temperaturas);
		contV++;
		cin >> temperaturas;
	}

	max = v[i];
	men = v[i];

	for (int j = 1; j < contV; j++)
	{
		if (max <= v[j])
		{
			max = v[j];
		}
		
		if (men >= v[j])
		{
			men = v[j];
		}
	}

	for (int j = 0; j < contV; j++)
	{
		if ((max == v[j]) && (men == v[j])) {
			contA++;
			contB++;
		}
		else if (men == v[j]) contB++;
		else if (max == v[j]) contA++;
	}

	cout << men << " " << contB << " " << max << " " << contA << endl;

	for (int i = 0; i < contV; i++)
	{
		v.pop_back();
	}
}*/

void criogenizacion()
{
	long long int temp, max, men;
	int contA = 1, contB = 1;

	cin >> temp;
	max = temp;
	men = temp;

	while (cin >> temp && temp != 0)
	{
		if (max < temp) {
			max = temp;
			contA = 1;
		}
		else if (temp == max) contA++;
		if (men > temp)
		{
			men = temp;
			contB = 1;
		}
		else if (temp == men) contB++;
	}

	cout << men << " " << contB << " " << max << " " << contA << endl;
}

int main()
{
	int casos;

	cin >> casos;

	for (int i = 0; i < casos; i++)
	{
		criogenizacion();
	}

	//system("pause");
	return 0;
}
