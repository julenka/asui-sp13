#!/usr/bin/env python
import csv

def main(argv):
    if (len(argv) < 2 ):
        print "Usage gen_names [in] [out]"
        exit(1)
    inf = argv[0]
    outf = argv[1]
    reader = csv.DictReader(open(inf, 'rU'))
    rows=[]
    for r in reader:
        name = filter(str.isalnum,r['Name'])
        r['Name'] = name
        r['numvowels'] = len([x for x in name if x in 'aeiouAEIOU'])
        r['length'] = len(name)
        r['endsina'] = name.endswith('a')
        rows.append(r)
    writer = csv.DictWriter(open(outf, 'w'), fieldnames=rows[0].keys())
    writer.writeheader()
    writer.writerows(rows)

if __name__ == '__main__':
    import sys
    main(sys.argv[1:])