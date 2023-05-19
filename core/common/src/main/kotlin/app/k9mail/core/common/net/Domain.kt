package app.k9mail.core.common.net

@JvmInline
value class Domain(val value: String) {
    init {
        requireNotNull(HostNameUtils.isLegalHostName(value)) { "Not a valid domain name: '$value'" }
    }
}
