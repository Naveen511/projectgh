export const navItems = [
    {
        name: 'Dashboard',
        url: '/dashboard',
        icon: 'icon-speedometer',
        badge: {
            variant: 'info',
            text: 'NEW'
        }
    },
    {
        title: true,
        name: 'Configuration'
    },
    {
        name: 'Head Office',
        url: '/configuration/head-office',
        icon: 'icon-folder'
    },
    {
        name: 'Zonal',
        url: '/configuration/zonal',
        icon: 'icon-folder'
    },
    {
        name: 'Sector',
        url: '/configuration/sector',
        icon: 'icon-folder-alt'
    },
    {
        name: 'Nursery',
        url: '/configuration/nursery',
        icon: 'icon-speech'
    },
    {
        name: 'Pick List',
        url: '/configuration/picklist',
        icon: 'icon-organization'
    },
    {
        name: 'Calendar Settings',
        url: '/configuration/financialYearSettings',
        icon: 'icon-equalizer'
    },
    {
        title: true,
        name: 'Production'
    },
    {
        name: 'Batch Formation',
        url: '/batch',
        icon: 'icon-layers'
    },
    {
        name: 'Stock Management',
        url: '/nurserystock',
        icon: 'icon-calculator'
    },
    {
        name: 'Damage Recording',
        url: '/damage',
        icon: 'icon-equalizer'
    },
    {
        name: 'Seeds & Cover Mgnt',
        url: '/nursery-inventory',
        icon: 'icon-layers'
    },
    // {
    //     name: 'Approval Management',
    //     icon: 'icon-disc'
    // },
    {
        title: true,
        name: 'Godown Management'
    },
    {
        name: 'Create Godown',
        url: '/godown/godownlist',
        icon: 'icon-layers'
    },
    {
        name: 'Purchase Details',
        url: '/godown/purchase',
        icon: 'icon-layers'
    },
    {
        name: 'Stock Details',
        url: '/godown/stock',
        icon: 'icon-layers'
    },
    {
        title: true,
        name: 'Audit Management'
    },
    {
        name: 'Audit Details',
        url: '/entity-audit/entity-audit',
        icon: 'icon-layers'
    }
];
