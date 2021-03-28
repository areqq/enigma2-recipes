# enigma2-recipes

```
git clone git://code.vuplus.com/git/openvuplus_3.0.git
cd openvuplus_3.0
make image MACHINE=vuduo2
```

Put into openvuplus_3.0/meta-openvuplus/recipes-extended
```
cd openvuplus_3.0/build/vuduo2
. ./bitbake.env
bitbake zerotier
bitbake -v n2n -c cleanall
```
