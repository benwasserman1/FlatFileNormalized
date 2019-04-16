from faker import Faker
import csv
import sys
import random

# still need email and description

fake = Faker()


def generate_random_num():
    result = 500/random.randint(1, 100)
    return result


def main(argv, tuples):
    with open(argv, mode='w') as flat_file:
        file_writer = csv.writer(flat_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)

        # write out
        for i in range(int(tuples)):
            name = fake.name()
            company_address = fake.address()
            company_country = fake.country()
            company_city = fake.city()
            company_zip = fake.zipcode()
            email = fake.email()
            address = fake.address()
            country = fake.country()
            city = fake.city()
            zip = fake.zipcode()
            ssn = fake.ssn()
            job = fake.job()
            company = fake.company()
            date_time = fake.date_time()
            number = fake.phone_number()
            currency = fake.currency()
            amount = generate_random_num()
            file_writer.writerow([name, email, address, country, city, zip, ssn, job, company, company_address, company_country, company_city, company_zip, date_time, number, currency, amount])
        

if __name__ == "__main__":
    main(sys.argv[1], sys.argv[2])

