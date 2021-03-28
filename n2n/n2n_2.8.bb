SUMMARY = "N2N Peer-to-peer VPN"
MAINTAINER = "https://github.com/ntop/n2n"
DESCRIPTION = "N2N is a light VPN software which makes it easy to create virtual networks bypassing intermediate firewalls."
HOMEPAGE = "https://github.com/ntop/n2n"
SECTION = "net"
LICENSE = "GPL3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d2dd9497ff2aa79327dc88b6ce2b03cc"

DEPENDS = "openssl"
RDEPENDS_${PN} = "kernel-module-tun"

inherit gitpkgv

SRCREV = "53afd3c067e1f9d2e37272e233104ef59d92507a"
PV = "2.8+git${SRCPV}"
PKGV = "2.8-1"

SRC_URI = "git://github.com/ntop/n2n;protocol=https;branch=2.8-stable \
        file://edge \
"
S = "${WORKDIR}/git"

INITSCRIPT_NAME = "edge"
inherit autotools-brokensep update-rc.d

do_configure() {
    sed -i 's|./co|#|' autogen.sh
    ./autogen.sh
    oe_runconf
}

do_compile() {
   oe_runmake
}

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/n2n
    install -m 0755 ${WORKDIR}/edge ${D}${sysconfdir}/init.d/edge
    install -m 0600 ${S}/packages/etc/n2n/edge.conf.sample ${D}${sysconfdir}/n2n/edge.conf
}
