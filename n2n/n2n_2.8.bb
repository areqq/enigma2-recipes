SUMMARY = "n2n is a light VPN software."
MAINTAINER = "https://github.com/zerotier/ZeroTierOne"
DESCRIPTION = "n2n is a light VPN software which makes it easy to create virtual networks bypassing intermediate firewalls."
HOMEPAGE = "https://github.com/ntop/n2n"
SECTION = "net"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=d2dd9497ff2aa79327dc88b6ce2b03cc"

RDEPENDS_${PN} = "kernel-module-tun"

inherit gitpkgv

SRCREV = "53afd3c067e1f9d2e37272e233104ef59d92507a"
PV = "2.8+git${SRCPV}"
PKGV = "2.8+git${GITPKGV}"

SRC_URI = "git://github.com/ntop/n2n;protocol=https;branch=2.8-stable \
        file://n2n \
        file://accept-external-ldflags.patch \
"

S = "${WORKDIR}/git"

INITSCRIPT_NAME = "n2n"

inherit autotools-brokensep update-rc.d systemd

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/zerotier ${D}${sysconfdir}/init.d/zerotier
}

INSANE_SKIP_${PN} = "already-stripped"
