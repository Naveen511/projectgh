/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 * -----------------------------------------------------------------------------
 * File Description : Declared an array variable to get sidebar option with name
 *                    to display in sidebar, url for link, icon for an option
 *                    as an object.
 *
 *******************************************************************************/

// Declare a variable with array of values
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
        icon: 'icon-globe'
    },
    {
        name: 'Zonal',
        url: '/configuration/zonal',
        icon: 'icon-share'
    },
    {
        name: 'Sector',
        url: '/configuration/sector',
        icon: 'icon-cursor'
    },
    {
        name: 'Nursery',
        url: '/configuration/nursery',
        icon: 'icon-home'
    },
    {
        name: 'Pick List',
        url: '/configuration/picklist',
        icon: 'icon-organization'
    },
    {
        name: 'Calendar Settings',
        url: '/configuration/financialYearSettings',
        icon: 'icon-calendar'
    },
    {
        title: true,
        name: 'Production'
    },
    {
        name: 'Batch Formation',
        url: '/batch',
        icon: 'icon-badge'
    },
    {
        name: 'Stock Management',
        url: '/nurserystock',
        icon: 'icon-calculator'
    },
    {
        name: 'Damage Recording',
        url: '/damage',
        icon: 'icon-note'
    },
    {
        name: 'Seeds & Cover Mgnt',
        url: '/nursery-inventory',
        icon: 'icon-bag'
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
        icon: 'icon-basket'
    },
    {
        name: 'Purchase Details',
        url: '/godown/purchase',
        icon: 'icon-grid'
    },
    {
        name: 'Stock Details',
        url: '/godown/stock',
        icon: 'icon-energy'
    },
    {
        title: true,
        name: 'Audit Management'
    },
    {
        name: 'Audit Details',
        url: '/entity-audit/entity-audit',
        icon: 'icon-notebook'
    }
];
